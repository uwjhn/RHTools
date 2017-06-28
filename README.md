# RHTools
Remote Hardware Tools [RHTools v0.004a]

## Eclipse Plugin
The RHTools eclipse plugin adds different functions to the eclipse IDE to
interact with a remote hardware device, like a raspberry pi.

### Install
The Plugin should be installedin eclipse via  
`Help --> Install New Software`   
The following updatesite should be used.  
`http://rhtools.digitizee.com/updatesite/` 

### Use
The plugin can be used via two icons, a menu or shortkeys.

#### Menu and Icons
* The left icon (see red rectangle) is used to run RHTools like it is configured.
* The right icon is used to configure RHTools.  
* In addition a menu can be used to operat RHTools (yellow rectangle)  
![RHTools Bar](https://github.com/uwlau/RHTools/blob/master/help/bar.png?raw=true)

#### Shortkeys
* Run RHTools: Ctrl + 6  
![Run Shortkey](https://github.com/uwlau/RHTools/blob/master/help/shortkey_exec.png?raw=true)
* Open Configurations: Ctrl + 7  
![Config Shortkey](https://github.com/uwlau/RHTools/blob/master/help/shortkey_config.png?raw=true)

#### Configuration
The configurations must be edited for the remote hardware. The checkboxes are
used to define if a specific task is done while running RHTools or not.  
![Config](https://github.com/uwlau/RHTools/blob/master/help/config.png?raw=true)

#### Run RHTools 
Running RHTools means that up to four tasks (as configured in the configurations)
are done automatically and step by step.

#### Other Functions
Other functions, like shutting down the device can be used via the menu.  
![Menu](https://github.com/uwlau/RHTools/blob/master/help/menu.png?raw=true)

#### Custom Command
The custom command menu-entry opens a dialog in which a custom command can be
entered which then will be operated on the remote hardware device.  
![Custom CMD](https://github.com/uwlau/RHTools/blob/master/help/custom_cmd.png?raw=true)

#### Console 
A Console shows outputs of the plugin.  
![Console](https://github.com/uwlau/RHTools/blob/master/help/console.png?raw=true)

#### Progress Bar
A progress bar shows if the RHTools currently runs a command.  
![Progress](https://github.com/uwlau/RHTools/blob/master/help/progress.png?raw=true)


## Shell-Scripts
The basic shell scipts can be used with unix terminal.
### Use
`./srciptname.sh config-name.cfg` 
