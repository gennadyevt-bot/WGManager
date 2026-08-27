# WG Manager

Android VPN-приложение с тёмной темой и красной 3D-нажимной кнопкой.

## Скриншоты

| Экран приложения | Иконка на домашнем экране |
|---|---|
| Тёмная тема, карточка сервера, кнопка VPN | Чёрный скруглённый квадрат с красным кругом |

## Особенности

- 🌑 **Тёмная тема** — фон `#121212`, акценты красным
- 🔴 **3D-кнопка VPN** — градиент, блик, тень, анимация нажатия (scale)
- 📡 **Сервер** — `DE-FRA-01` с цветным статусом
- 🎨 **Адаптивная иконка** — скруглённый чёрный квадрат с красной круглой кнопкой и надписью VPN
- ✅ **Исправлен баг** — `statusDot` теперь `View` + `setBackgroundColor` (был `CardView`)

## Статусы подключения

| Статус | Цвет |
|---|---|
| Disconnected | 🔴 Красный `#E53935` |
| Connecting | 🟠 Оранжевый `#FFA726` |
| Connected | 🟢 Зелёный `#4CAF50` |

## Технологии

- Kotlin
- Android SDK 34
- Material Design 3
- ViewBinding
- ConstraintLayout
- CardView

## Сборка

```bash
# Открыть в Android Studio
File → Open → выбрать папку WGManager

# Синхронизировать Gradle
Sync Project with Gradle Files

# Запустить
Run → Run 'app'
```

## Структура проекта

```
WGManager/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/wgmanager/
│   │   │   └── MainActivity.kt
│   │   ├── res/
│   │   │   ├── layout/activity_main.xml
│   │   │   ├── drawable/
│   │   │   │   ├── vpn_button_bg.xml
│   │   │   │   ├── button_shadow.xml
│   │   │   │   ├── dot_disconnected.xml
│   │   │   │   └── ic_launcher_foreground.xml
│   │   │   ├── values/
│   │   │   │   ├── colors.xml
│   │   │   │   ├── strings.xml
│   │   │   │   └── themes.xml
│   │   │   └── mipmap-anydpi-v26/
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Лицензия

MIT
