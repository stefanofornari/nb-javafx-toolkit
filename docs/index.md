# <img src="logo.svg" width="64" height="64" alt="JavaFX Toolkit Logo" style="vertical-align: middle;"> JavaFX Toolkit for NetBeans

NetBeans wrapper module that provides to NetBeans plug-ins top quality FX libraries and components.

Built to empower NetBeans developers with modern UI capabilities using the latest JavaFX ecosystem libraries.

## Included Libraries

- **JavaFX** v21.0.10: The core platform for modern Java UI development.
- **FormsFX** v26.0.2: Easily create form-based UIs.
- **PreferencesFX** v26.0.2: Manage application preferences with ease.
- **ControlsFX** v11.2.3: High-quality UI controls and tools.
- **AnimateFX** v1.2.4: Ready-to-use animations for JavaFX.
- **Ikonli Icons Pack** v12.4.0: A wide variety of icon packs for JavaFX.
- **AtlantaFX** v2.1.0: Modern CSS themes for JavaFX.

## Installation

### Requirements

- **NetBeans IDE**: Version 14 or later.
- **Java**: JDK 21 or later.

### Step-by-Step Installation

1. **Download the NBM**: Obtain the latest `.nbm` file from the [releases](https://github.com/stefanofornari/nb-javafx-toolkit/releases) page.
2. **Open NetBeans**: Launch your NetBeans IDE.
3. **Plugins Manager**: Go to **Tools** &rarr; **Plugins**.
4. **Manual Install**:
   - Click the **Downloaded** tab.
   - Click **Add Plugins...**.
   - Select the downloaded `.nbm` file.
   - Click **Install** and follow the wizard.
5. **Restart**: Restart NetBeans to complete the installation.

## Usage

Once installed, your NetBeans plugins can depend on `JavaFX Toolkit for NetBeans` to access the included libraries. Make sure to add the module as a dependency in your project's `pom.xml` or NetBeans module configuration.

## Building from Source

If you wish to build the toolkit yourself:

```bash
git clone https://github.com/stefanofornari/nb-javafx-toolkit.git
cd nb-javafx-toolkit
mvn clean package
```

The resulting `.nbm` file will be in the `target` directory.

---
**License**: Multiple (see [LICENSES](https://github.com/stefanofornari/nb-javafx-toolkit/tree/main/LICENSES) folder)  
**Author**: Stefano Fornari
