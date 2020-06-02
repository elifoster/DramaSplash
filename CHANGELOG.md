# Changelog
## Version 4
* Upgrade to Minecraft 1.14–1.15 (#5, #6).

## Version 3
* Upgrade to Minecraft 1.13–1.13.2 (#4).

## Version 2
### 2.0.4
* Set max accepted Minecraft version to 1.12.2, as 1.13+ does not work yet.
* Change error splash text from "Check your logs!" to "Drama Generator fails to generate! [check your logs!]" (xbony2) (#2)
* Change modid to match new Forge requirements as of Minecraft 1.11. This fixes the mod not loading properly on 1.11+.
* Officially supports 1.12 now (#3).

### 2.0.3
* Now supports any version above 1.8, because I am sick of updating the accepted versions (thank
you Maven for sucking at semantic versions; see Ruby `~>`). The code this modifies very rarely
gets changed so I think this is fairly safe. Now works on 1.11.

### 2.0.2
* Set the acceptedMinecraftVersions parameter to support 1.10.2.

### 2.0.1
* Set the acceptedMinecraftVersions parameter to support 1.9 and 1.10.

### 2.0.0
* Update to Minecraft 1.8.9.

## Version 1
### 1.1.0
* Update to use the new API at mc-drama/raw. This improves loading performance as it does not
require performing regex or loops. (#1)
* Close the reader BEFORE we return, rather than after. This should also improve performance.

### 1.0.0
* Initial release.