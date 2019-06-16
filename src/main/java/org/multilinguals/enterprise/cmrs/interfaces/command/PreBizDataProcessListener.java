package org.multilinguals.enterprise.cmrs.interfaces.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.multilinguals.enterprise.cmrs.command.handler.preprocess.InitializeUserDataCommand;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 应用启动时，进行业务数据的预处理
 */
@Component
public class PreBizDataProcessListener implements ApplicationListener<ApplicationStartedEvent> {
    @Resource
    private CommandGateway commandGateway;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        commandGateway.send(new InitializeUserDataCommand());
    }
}
