# FixQueryUsernameLeak

This plugin enforces the player's "Allow Server Listings" client setting when replying to query requests (for example using https://github.com/py-mine/mcstatus)

Has no effect unless `enable-query=true` in `server.properties` (set to false by default)

With this client option is set to off:

![Options > Online > Allow Server Listings: Off](/images/AllowServerListingsOff.png)

Without plugin:

```
$ mcstatus localhost query
host: 192.168.1.10:25565
software: v1.19.4 Paper on 1.19.4-R0.1-SNAPSHOT
plugins: []
motd: "A Minecraft Server"
players: 1/20 ['Steve']
```

With plugin:
```
$ mcstatus localhost query
host: 192.168.1.10:25565
software: v1.19.4 Paper on 1.19.4-R0.1-SNAPSHOT
plugins: ['FixQueryUsernameLeak 1.0']
motd: "A Minecraft Server"
players: 1/20 ['Anonymous Player']
```