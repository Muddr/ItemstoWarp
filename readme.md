# ItemstoWarp

A pay-to-warp plugin that can use any item as a currency to warp players to warps that they set up.

## Quick Info
- Current Version: 1.2.1
- CB Version: CB 1.3.2-R0.2
- Changelog: <https://github.com/Muddr/ItemstoWarp/blob/master/changelog.md>

## Features

- Ability to create and remove warps
- Can toggle warps between public and private
- Ability to warp using signs
- List for all public warps
- Personal list of warps
- Permissions for all features
	
## Configuration

### Default config.yml:
	currency:
	  amount: 20
	  item: 331
	  freeforcreative: false

	sign:
	  protected: true
	  updatetool: 280
	  text:
		public: ''
		private: 'Private'

	show:
	  cords: true
	  world: true

	DB:
	  driver: 'org.sqlite.JDBC'
	  url: 'jdbc:sqlite:{DIR}{NAME}.db'
	  username: 'root'
	  password: ''
	  isolation: 'SERIALIZABLE'
	  logging: false
	  rebuild: false

### Explanation
All settings will be shown in node syntax for ease of documentation, e.g. `currency.amount` will refer to the `amount` setting in the `currency` group.

`currency.amount`
- Amount of items needed to warp.
- Default: `20`

`currency.item`
- Item ID of the item to be used as currency.
- Default: `331` (Redstone Dust)

`currency.freeforcreative`
- Whether players in creative mode can warp for free.
- Default: `false`

`sign.protected`
- Whether signs are protected from being destroyed. *Note: the owner of the warp can always destroy the sign.*
- Default: `true`

`sign.updatetool`
- Item ID of the item that is used to update sign's privacy *without warping*.
- Default: `280` (Stick)

`sign.text.public`
- The text shown on signs for "Public" warps.
- Default: (blank, to match prior version behavior; recommended alternative: `Public`)

`sign.text.private`
- The text shown on signs for "Private" warps.
- Default: `Private`

`show.coords`
- Whether to display the warp coordinates on the `/itw list` and `/itw mylist` commands.
- Default: `true`

`show.world`
- Whether to display the world name a warp is in on the `/itw list` and `/itw mylist` commands.
- Default: `true`

#### DB related settings
It is highly recommended that this section of the config be left as-is. **Only attempt changing this section if you know what you are doing!**

**Issues regarding database settings will not be answered.** They will be closed as we will most likely not be able to answer them. This section is provided as a courtesy for advanced users if they need it.

## Commands
- `/itw create "name"`
	Creates a warp at your location.
- `/itw toggle "name"`
	Toggles the warp from public/private.
- `/itw remove "name"`
	Removes the warp.
- `/itw warp "name"`
	Warps to "name".
- `/itw list`
	Lists all public warps.
- `/itw mylist`
	Lists all your warps.

## Permissions
- `itemstowarp.create`
	Gives the ability to create warps.
- `itemstowarp.makeprivate`
	Gives the ability to make a warp private.
- `itemstowarp.makeprivate.any`
	Gives the ability to make **any** warp private.
- `itemstowarp.remove`
	Gives the ability to remove a warp.
- `itemstowarp.remove.any`
	Gives the ability to remove **any** warp.
- `itemstowarp.warp`
	Gives the ability to use warps
- `itemstowarp.warp.any`
	Gives the ability to use **any** warp, including private warps.
- `itemstowarp.warp.nocost`
	Gives the ability to bypass the warp fee when warping.
- `itemstowarp.warp.sign`
	Gives the ability to use sign warps.
- `itemstowarp.warp.sign.create`
	Gives the ability to create a sign warp.
- `itemstowarp.warp.sign.removeany`
	Gives the ability to remove **any** sign warp.
- `itemstowarp.list`
	Gives the ability to list all public warps.
- `itemstowarp.list.all`
	Gives the ability to list all warps, including private warps.
- `itemstowarp.list.own`
	Gives the ability to all the warps you created.

### Permission Sets
These permissions include a set of the above permissions that can be used as some sensible defaults. The permissions included are listed below the set permission.

**`itemstowarp.user`**
- `itemstowarp.create`
- `itemstowarp.makeprivate`
- `itemstowarp.remove`
- `itemstowarp.warp`
- `itemstowarp.warp.sign`
- `itemstowarp.warp.sign.create`
- `itemstowarp.list`
- `itemstowarp.list.own`

**`itemstowarp.admin`**
- `itemstowarp.create`
- `itemstowarp.makeprivate.any`
- `itemstowarp.remove.any`
- `itemstowarp.warp.any`
- `itemstowarp.warp.sign`
- `itemstowarp.warp.sign.create`
- `itemstowarp.warp.sign.removeany`
- `itemstowarp.list.all`
- `itemstowarp.list.own`

## Creating Sign Warps
To create a sign warp place a sign with the following information:
- **Line 1:** `[Warp]`
- **Line 2:** Name of the warp. Ex: `mywarp`

*Note: both lines are case insensitive. Ex: `[WaRp]`, `[wArP]` as well as any variant will all work.*

**Example:**
![Warp sign example] (https://dl.dropbox.com/u/18835236/ItemstoWarp/ItemstoWarp-Signs.png)

#### After the sign has been created
Once the sign has been placed, there will be a few more lines automatically added on to the sign. Using the above information while creating the sign, the final sign will look something like this:

- **Line 1:** `[warp]`
- **Line 2:** `mywarp`
- **Line 3:** `MyUsername`
- **Line 4:** (blank) OR `Private`
	- *Note: this line be the text from the config setting `sign.text.public` or `sign.text.private`, depending on the warp's privacy.*

##### A note about signs and warp privacy
The privacy of the warp that is shown on the sign is the privacy *at the time the sign was created*. When a warp's privacy is changed, signs that have already been created will not be updated to show the new privacy status. *Note: a warp's privacy setting will be checked before any warp occurs, so changing your public warp to a private one is safe.*

The sign's privacy status will be updated when someone attempts to warp by using the sign. To update without warping, right-click on the sign while holding the item as defined in the config setting `sign.updatetool`.