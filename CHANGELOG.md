# Visual Novel Engine — Changelog

## v0.1.2 (Main Menu & Flow Update)
- Added Main Menu displayed immediately on startup
- Added 'Start Game' and 'Exit' options
- Toolbar visibility now depends on GameState
- Implemented clean transitions between gameplay and menu
- Fixed double Enter bug at end of story
- Improved input handling and exit() behavior
- Restarting game from menu now reloads story properly
- Introduced smoother control flow and cleaner scene switching
- General polish and code cleanup across core classes

This update finalizes the core game loop flow between
Menu ↔ Gameplay ↔ Exit, preparing the engine for future
features like Save/Load and Settings screens.

---

## v0.1.1a (Hotfix)
- Fixed non-functional **Exit** button in toolbar
- Removed obsolete `exit()` method from `GameManager`
- Adjusted toolbar listener behavior for proper termination
- Minor code cleanup and comment adjustments
- Verified proper closing of application window

Small maintenance release ensuring consistent exit behavior
and improved reliability of the user interface.

---

## v0.1.1 (Core Refactor & Structure Update)
- Reworked game flow: moved main loop logic from `GameLoop` into `GameManager`
- Introduced `GameState` enum (`MAIN_MENU`, `PLAYING`, `PAUSED`, `EXITING`)
- Added `MenuManager` class to handle main menu and toolbar input
- Implemented `SceneController` to manage active scenes and transitions
- Simplified `InputHandler` and updated console behavior
- Cleaned up redundant logic and removed unused methods
- Improved consistency between `RenderManager`, `ConsoleWindow` and `StoryInitializer`
- Prepared structure for upcoming save/load system and menu integration

Major internal refactor that unified game logic and introduced
a clean separation between menu, game state, and rendering flow.
Prepared the foundation for future save/load implementation.

---

## v0.1 (Initial public milestone)
- Introduced Block-based architecture (Narrative, Dialogue, Monologue, Choice)
- Created `Scene` container structure for story flow
- Implemented main game loop (render/input/update)
- Integrated `FancyConsoleWindow` renderer
- Added typing animation for text output
- Introduced Enter-to-continue flow between blocks
- Added first test story (TestStory.java) for functional validation

First working public version of the Visual Novel Engine.
Introduced the fundamental story-block architecture
and interactive console-based storytelling system.
