<h3>ItemstoWarp - A pay to warp system.</h3>

<strong>Features</strong><br />
<br />
<br />
<br />
<strong>Configuration</strong><br />
currency:<br />
&nbsp;&nbsp;&nbsp;&nbsp;amount: 20          # Amount needed to warp.<br />
&nbsp;&nbsp;&nbsp;&nbsp;item: 331           # Material Id of the item to be used for warping. (Default item 331 = Redstone)<br />
sign:<br />
&nbsp;&nbsp;&nbsp;&nbsp;protected: true     # Should warp signs be protected from being removed. (Warp owner can remove sign)<br />


  <strong>Commands</strong>
<ul>
	<li>/itw create "name" - Creates a warp at your location.</li>
	<li>/itw toggle "name" - Toggles the warp from public/private.</li>
	<li>/itw remove "name" - Removes the warp.</li>
	<li>/itw warp "name" - Warps to "name".</li>
	<li>/itw list - Lists all public warps.</li>
	<li>/itw mylist - Lists all your warps.</li>
</ul>


<strong>Permissions</strong>
<ul>
	<li>itemstowarp.create - Gives the ability to create warps.</li>
	<li>itemstowarp.makeprivate - Gives the ability to make a warp private.</li>
	<li>itemstowarp.makeprivate.any - Gives the ability to make <strong>any</strong> warp private.</li>
	<li>itemstowarp.remove - Gives the ability to remove a warp.</li>
	<li>itemstowarp.remove.any - Gives the ability to remove <strong>any</strong> warp.</li>
	<li>itemstowarp.warp - Gives the ability to use warps</li>
	<li>itemstowarp.warp.any - Gives the ability to use <strong>any</strong> warp, including private warps.</li>
	<li>itemstowarp.warp.nocost - Gives the ability to bypass the warp fee when warping.</li>
	<li>itemstowarp.warp.sign - Gives the ability to use sign warps.</li>
	<li>itemstowarp.warp.sign.create - Gives the ability to create a sign warp.</li>
	<li>itemstowarp.warp.sign.removeany - Gives the ability to remove <strong>any</strong> sign warp.</li>
	<li>itemstowarp.list - Gives the ability to list all public warps.</li>
	<li>itemstowarp.list.all - Gives the ability to list all warps, including private warps.</li>
	<li>itemstowarp.list.own - Gives the ability to all the warps you created.</li>
</ul>
<ul>
	<li>itemstowarp.user - includes the following permissions:
	<p>
	<ul>
	<li>itemstowarp.create</li>
	<li>itemstowarp.makeprivate</li>
	<li>itemstowarp.remove</li>
	<li>itemstowarp.warp</li>
	<li>itemstowarp.warp.sign</li>
	<li>itemstowarp.warp.sign.create</li>
	<li>itemstowarp.list</li>
	<li>itemstowarp.list.own</li></ul></p></li>
	<li>itemstowarp.admin - includes the following permissions:
 	<p>
	<ul>
	<li>itemstowarp.create</li>
	<li>itemstowarp.makeprivate.any</li>
	<li>itemstowarp.remove.any</li>
	<li>itemstowarp.warp.any</li>
	<li>itemstowarp.warp.sign</li>
	<li>itemstowarp.warp.sign.create</li>
	<li>itemstowarp.warp.sign.removeany</li>
	<li>itemstowarp.list.all</li>
	<li>itemstowarp.list.own</li></ul></p></li>
</ul>
<strong>Sign Warps</strong><br />
To create a sign warp place a sign with the following information:<br />
Line 1: [Warp]    Note: this is case insensitive. [warp] [WaRp] [warP] will all work.<br />
Line 2: the name used when you created the warp.<br />