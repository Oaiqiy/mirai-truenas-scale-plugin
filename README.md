# mirai-truenas-scale-plugin

QQ bot for truenas scale

对于truenas的支持来源为 [truenas-scale-sdk](https://github.com/Oaiqiy/truenas-scale-sdk)
支持的命令列表 在 [TrueNasCommand](https://github.com/Oaiqiy/truenas-scale-sdk/blob/master/src/main/java/dev/oaiqiy/truenas/scale/sdk/common/TrueNasCommand.java)
目前对于参数还没写说明文档,需要到代码中查看具体的参数定义

示例命令如下

`unlock [dataset] [passphrase] <force>` 解锁一个数据集`dataset`,使用密码为 `passphrase`,`force`表示是否强制,为可选项

`app start [releaseName]` 启动一个名为`releaseName`的truenas scale应用

`app stop [releaseName]` `app restart [releaseName]` 同理

## Config

config/dev.oaiqiy.truenas-plugin/config.yml

`URL_PREFIX` truenas scale api url前缀 e.g. http://localhost/api/v2.0
`TOKEN` truenas scale api token