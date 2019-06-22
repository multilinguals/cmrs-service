package org.multilinguals.enterprise.cmrs.query.user;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.enterprise.cmrs.command.aggregate.account.event.AccountBoundUserEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.password.event.UserPasswordBoundUserEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.UserCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.user.event.UserDetailsUpdatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.event.UserSessionCreatedEvent;
import org.multilinguals.enterprise.cmrs.command.aggregate.usersession.event.UserSessionDeletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserDetailsViewEventHandler {

    private UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    public UserDetailsViewEventHandler(UserDetailsViewRepository userDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
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
        userDetailsView.setRealName(event.getRealName());
        Role role = new Role(event.getRoleId().getIdentifier(), event.getRoleId().getRoleName(), new Date(createdTime.toEpochMilli()));
        userDetailsView.setRole(role);
        this.userDetailsViewRepository.save(userDetailsView);
    }

    /**
     * 当账号已绑定用户时，视图记录增加账号信息
     *
     * @param event       账号已绑定用户事件
     * @param createdTime 事件发生时间
     * @throws ChangeSetPersister.NotFoundException
     */
    @EventHandler
    public void on(AccountBoundUserEvent event, @Timestamp java.time.Instant createdTime) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        UserAccount userAccount = new UserAccount(
                event.getAccountId().getIdentifier(),
                event.getAccountId().getIdInType(),
                event.getAccountId().getType(),
                new Date(createdTime.toEpochMilli())
        );
        userDetailsView.setUserAccount(userAccount);

        this.userDetailsViewRepository.save(userDetailsView);
    }

    /**
     * 当密码绑定用户时，视图增加密码信息
     *
     * @param event 密码已绑定用户事件
     * @throws ChangeSetPersister.NotFoundException
     */
    @EventHandler
    public void on(UserPasswordBoundUserEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        userDetailsView.setUserPasswordId(event.getUserPasswordId().getIdentifier());

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

    public void on(UserDetailsUpdatedEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (event.getRealName() != null) {
            userDetailsView.setRealName(event.getRealName());
        }

        this.userDetailsViewRepository.save(userDetailsView);
    }
}
