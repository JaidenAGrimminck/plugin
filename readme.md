# Plugin for FTC

This is a plugin for a Minecraft server that can be connected via websocket to our FTC robot.

To use this plugin, first run:

```bash
git clone https://github.com/jaidenagrimminck/plugin.git
```

Then, open the project in your IDE and run Maven package.

After that, copy the jar file from the target folder to your server's plugins folder.

Finally, start your server and connect to it with the robot.

# Robot Code

The corresponding robot code can be found on the "main" branch of https://github.com/JaidenAGrimminck/plugin.

# Usage

To use this plugin, chat "`connect`" to connect to the robot.
Usage:
```
connect [robot ip?]
```

and to disconnect, chat "`stop`".