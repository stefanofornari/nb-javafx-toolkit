# JavaFX Toolkit for NetBeans

NetBeans wrapper module providing top-quality JavaFX libraries and components for modern UI development.

## Bundled Libraries

- **[JavaFX](https://openjfx.io/) v21.0.10** (Base, Controls, FXML, Graphics, Media, Swing)
- **[FormsFX](https://github.com/stefanofornari/formsfx)(1) v26.0.2**
- **[PreferencesFX](https://github.com/stefanofornari/preferencesfx)(1) v26.0.2**
- **[ControlsFX](https://github.com/controlsfx/controlsfx) v11.2.3**
- **[AnimateFX](https://github.com/Typhon0/AnimateFX) v1.2.4**
- **[Ikonli Icons Pack](https://github.com/kordamp/ikonli) v12.4.0**
- **[AtlantaFX](https://github.com/mkpaz/atlantafx) v2.1.0**

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
    <version>0.0.2-SNAPSHOT</version>
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