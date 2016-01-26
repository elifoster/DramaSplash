# Changelog
## Version 1
### 1.1.0
* Update to use the new API at mc-drama/raw. This improves loading performance as it does not
require performing regex or loops. (#1)
* Close the reader BEFORE we return, rather than after. This should also improve performance.

### 1.0.0
* Initial release.