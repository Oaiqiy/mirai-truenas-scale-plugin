package dev.oaiqiy.mirai.truenas.scale.plugin;

import com.alibaba.fastjson2.JSON;
import dev.oaiqiy.truenas.scale.sdk.TrueNasClient;
import dev.oaiqiy.truenas.scale.sdk.common.TrueNasConfig;
import dev.oaiqiy.truenas.scale.sdk.exception.TrueNasException;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public final class TrueNasPlugin extends JavaPlugin {
    public static final TrueNasPlugin INSTANCE = new TrueNasPlugin();

    public static volatile Long ADMIN_NUMBER;

    private TrueNasPlugin() {
        super(new JvmPluginDescriptionBuilder("dev.oaiqiy.truenas-plugin", "0.1.0")
                .info("EG")
                .build());
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        reloadPluginConfig(Config.INSTANCE);
        if (StringUtils.isBlank(Config.INSTANCE.getURL_PREFIX())) {
            getLogger().error("未填写 url prefix");
        }
        if (StringUtils.isBlank(Config.INSTANCE.getTOKEN())) {
            getLogger().error("未填写 token");
        }

        TrueNasConfig.URL_PREFIX = Config.INSTANCE.getURL_PREFIX();
        TrueNasConfig.TOKEN = Config.INSTANCE.getTOKEN();
        ADMIN_NUMBER = Config.INSTANCE.getADMIN_NUMBER();
    }

    @Override
    public void onEnable() {
        getLogger().info("日志");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);

        eventChannel.subscribeAlways(FriendMessageEvent.class, f -> {
            if (Objects.nonNull(ADMIN_NUMBER)
                    && ADMIN_NUMBER != 0L
                    && f.getSender().getId() != ADMIN_NUMBER) {
                f.getSender().sendMessage("没有管理员权限");
                return;
            }

            //监听好友消息
            try {
                Map<String, Object> response = TrueNasClient.execute(f.getMessage().contentToString());
                f.getSender().sendMessage(JSON.toJSONString(response));
            } catch (TrueNasException e) {
                f.getSender().sendMessage(e.getMessage());
            } catch (Exception e) {
                f.getSender().sendMessage(e.toString());
            }
        });

    }

}
