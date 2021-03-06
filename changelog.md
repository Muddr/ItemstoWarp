# v2.0.0 - Works with CB 1.2.4-R1.0 - CB 1.4.6-R0.1+
- Added: Ability to import warps from other plugins. Currently only from EcoWarp.
    - Permissions for importing default to op and those with 'itemstowarp.admin' permission
- **Major Change:** Database Structure - Added pitch and yaw to warps.
    - **Database will be backed up and converted on first run**
- Fixed: Error where you couldn't warp if you had the exact amount needed.

# v1.2.1 - Works with CB 1.3.2-R0.2
- Fixed: Incorrect if statement causing an error when right clicking signs.

# v1.2.0 - Works with CB 1.3.2-R0.2
- Added: config option to choose sign text for private/public warp status.
	- Defaults have stayed the same; "" for public warps and "Private" for private warps.
- Changed: signs now update warp privacy status when trying to warp.
	- Added config option to use a tool to update the status without attempting to warp.
- Added: config option to allow players in creative mode to warp for free.
- Fixed: color parsing issue with an error message.

# v1.1.1 - Works with CB 1.3.1-R1.0
- Fixed: warp offset that was causing players to warp into walls adjusted.

# v1.1.0 - Works with CB 1.2.4-R1.0
- Added: ability to hide warp's world name from the `list` and `mylist` commands.
	- Added option in the config for this setting.
- Added: ability to hide warp's coordinates from the `list` and `mylist` commands.
	- Added option in the config for this setting.
- Removed: paging options for `list` and `mylists` commands now that chat scrolls.

# v1.0.1 - Works with CB 1.1-R6 to DEV 1.2.3-R0.3
- Fixed: permission issue removing sign warps

# v1.0.0
- Initial release.