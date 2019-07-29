package org.multilinguals.enterprise.cmrs.query.user;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.AbstractAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.RolesSetToUserEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.UserCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.UserDetailsUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.event.UserSessionCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.event.UserSessionDeletedEvent;
import org.multilinguals.enterprise.cmrs.query.rbac.RoleDetailsView;
import org.multilinguals.enterprise.cmrs.query.rbac.RoleDetailsViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDetailsViewEventHandler {

    private UserDetailsViewRepository userDetailsViewRepository;

    private RoleDetailsViewRepository roleDetailsViewRepository;

    @Autowired
    public UserDetailsViewEventHandler(UserDetailsViewRepository userDetailsViewRepository, RoleDetailsViewRepository roleDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
        this.roleDetailsViewRepository = roleDetailsViewRepository;
    }

    /**
     * 响应用户已创建事件，并创建用户视图记录
     *
     * @param event       用户已创建事件
     * @param createdTime 事件发生的时间
     */
    @EventHandler
    public void on(UserCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        UserDetailsView userDetailsView = new UserDetailsView(event.getId().getIdentifier(), new Date(createdTime.toEpochMilli()));

        // 设置姓名
        userDetailsView.setRealName(event.getRealName());

        // 设置账号
        UserAccount userAccount = new UserAccount(
                event.getAccountId().getIdentifier(),
                event.getAccountId().getIdInType(),
                event.getAccountId().getType(),
                new Date(createdTime.toEpochMilli())
        );
        userDetailsView.setUserAccount(userAccount);

        // 设置角色
        Role role = new Role(event.getRoleId().getIdentifier(), event.getRoleId().getName(), new Date(createdTime.toEpochMilli()));
        userDetailsView.setRole(role);

        // 设置关联密码
        userDetailsView.setUserPasswordId(event.getUserPasswordId().getIdentifier());

        this.userDetailsViewRepository.save(userDetailsView);
    }

    @EventHandler
    public void on(RolesSetToUserEvent event, @Timestamp java.time.Instant createdTime) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .map(user -> {
                    List<String> roleIdStringList = new ArrayList<>();

                    for (AbstractAggregateIdentifier identifier : event.getRoleIdList()) {
                        roleIdStringList.add(identifier.getIdentifier());
                    }

                    Iterable<RoleDetailsView> roleDetailsViews = this.roleDetailsViewRepository.findAllById(roleIdStringList);

                    user.getRoleViewList().clear();

                    for (RoleDetailsView roleDetailsView : roleDetailsViews) {
                        user.setRole(new Role(roleDetailsView.getId(), roleDetailsView.getName(), new Date(createdTime.toEpochMilli())));
                    }

                    user.setUpdatedAt(new Date(createdTime.toEpochMilli()));
                    return user;
                })
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        this.userDetailsViewRepository.save(userDetailsView);
    }

    /**
     * 当用户登录时，视图增加会话信息
     *
     * @param event 用户会话已创建事件
     * @throws ChangeSetPersister.NotFoundException
     */
    @EventHandler
    public void on(UserSessionCreatedEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        userDetailsView.setUserSessionId(event.getUserSessionId().getIdentifier());
        userDetailsView.setUserSessionExpiredAt(event.getExpiredAt());
        this.userDetailsViewRepository.save(userDetailsView);
    }

    /**
     * 当用户退出登录时，视图更新会话信息
     *
     * @param event 用户会话已删除事件
     * @throws ChangeSetPersister.NotFoundException
     */
    @EventHandler
    public void on(UserSessionDeletedEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (userDetailsView.getUserSessionId().equals(event.getUserSessionId().getIdentifier())) {
            userDetailsView.setUserSessionId(null);
            userDetailsView.setUserSessionExpiredAt(null);
        }

        this.userDetailsViewRepository.save(userDetailsView);
    }

    /**
     * 用户详情更新时，视图更新用户信息
     *
     * @param event
     * @throws ChangeSetPersister.NotFoundException
     */
    @EventHandler
    public void on(UserDetailsUpdatedEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (event.getRealName() != null) {
            userDetailsView.setRealName(event.getRealName());
            userDetailsView.setUpdatedAt(new Date());
        }

        this.userDetailsViewRepository.save(userDetailsView);
    }
}
