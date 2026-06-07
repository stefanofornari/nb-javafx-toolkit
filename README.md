# JavaFX Toolkit for NetBeans

NetBeans wrapper module providing top-quality JavaFX libraries and components for modern UI development.

## Bundled Libraries

- **[JavaFX](https://openjfx.io/) v21.0.10**: The core platform for modern Java UI development.
- **[FormsFX](https://github.com/stefanofornari/formsfx) v26.0.4**: Easily create form-based UIs.
- **[PreferencesFX](https://github.com/stefanofornari/preferencesfx) v26.0.5**: Manage application preferences with ease.
- **[ControlsFX](https://github.com/controlsfx/controlsfx) v11.2.3**: High-quality UI controls and tools.
- **[AnimateFX](https://github.com/Typhon0/AnimateFX) v1.2.4**: Ready-to-use animations for JavaFX.
- **[Ikonli Icons Pack](https://github.com/kordamp/ikonli) v12.4.0**: A wide variety of icon packs for JavaFX.
- **[AtlantaFX](https://github.com/mkpaz/atlantafx) v2.1.0**: Modern CSS themes for JavaFX.
- **[CommonsFX](https://github.com/stefanofornari/preferencesfx) v0.0.1**: Java FX commodities.

## Quick Start

1. Download the latest `.nbm` from [Releases](https://github.com/stefanofornari/nb-javafx-toolkit/releases).
2. In NetBeans: **Tools** -> **Plugins** -> **Downloaded** -> **Add Plugins...**.
3. Install and restart.

## Maven Dependency

Add this to your NetBeans module's `pom.xml`:

```xml
<dependency>
    <groupId>com.github.stefanofornari</groupId>
    <artifactId>nb-javafx-toolkit</artifactId>
    <version>0.0.2</version>
    <type>nbm</type>
</dependency>
```

## Build

```bash
mvn clean install
```

## Documentation

Full documentation and guides are available at [https://stefanofornari.github.io/nb-javafx-toolkit/](https://stefanofornari.github.io/nb-javafx-toolkit/).

## Notes
1. Derived version from https://github.com/dlsc-software-consulting-gmbh