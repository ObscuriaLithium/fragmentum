## [5.4.0] - 2026-10-15

* Added a **Lite** version under **Additional Files** without bundled dependencies, ideal for modpack creators who prefer to manage Fragmentum dependencies manually or already include them in their modpack

## [5.3.0] - 2026-10-12

* Added the `AssetRegistry` API with support for resource reloading
* Added support for `stringFieldOption` in `ConfigScreenFactory`
* Extended `Deferred` with `Lazy` and optimized `DeferredImpl` initialization

## [5.2.0] - 2026-10-05

* `ForgeRegistrar.createRegistry` now creates vanilla mapped registries instead of Forge-specific registries
* Include MixinExtras for Forge

## [5.1.1] - 2026-09-30

* Added support for `buttonOption` in `ConfigScreenFactory`

## [5.0.0] - 2026-09-19

* Migrated to **Modern Stonecutter** + **Kotlin** for improved maintainability and extensibility. This introduces breaking changes for mods that depend on Fragmentum, so please make sure to update them as well
