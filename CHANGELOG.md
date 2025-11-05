# Visual Novel Engine — Changelog

## v0.1.2 (Main Menu & Flow Update)
- Added Main Menu displayed immediately on startup
- Added 'Start Game' and 'Exit' options
- Toolbar visibility now depends on GameState
- Clean transitions between gameplay and menu
- Fixed double Enter bug at end of story
- Improved input handling and exit() behavior
- Restarting game from menu now reloads story properly

This update finalizes the core game loop flow between
Menu ↔ Gameplay ↔ Exit, preparing the engine for future
features like Save/Load and Settings screens.

---

## v0.1.1a (Hotfix)
- Fixed non-functional **Exit** button in toolbar
- Removed obsolete `exit()` method from `GameManager`
- Minor code cleanup and comments adjustment

---

## v0.1.1 (Core Refactor & Structure Update)
- Reworked game flow: moved main loop logic from `GameLoop` into `GameManager`
- Introduced `GameState` enum (`MAIN_MENU`, `PLAYING`, `PAUSED`, `EXITING`)
- Added `MenuManager` class to handle main menu and toolbar input
- Implemented `SceneController` to manage active scenes and transitions
- Cleaned up redundant logic and removed unused methods
- Prepared structure for upcoming save/load system

---

## v0.1 (Initial public milestone)
- Introduced Block-based architecture (Narrative, Dialogue, Monologue, Choice)
- Scene container structure
- Game loop (render/input/update)
- Integrated FancyConsoleWindow renderer
- Typing animation for text
- Enter-to-continue flow
