# Visual Novel Engine — Changelog

## v0.1.1a (Hotfix)
**Release date:** _2025-10-31_

### 🛠️ Hotfix
- Fixed non-functional **Exit** button on toolbar — now gracefully closes the game

## v0.1.1 (Refactor & Visual Update)
**Release date:** _2025-10-31_  
**Stage:** Stable internal milestone before menu implementation

### Core Engine
- Introduced **`SceneController`** to handle active scene navigation and block progression  
- Extracted all game logic from **`GameLoop`** into **`GameManager`** and **`SceneController`**, making the loop clean and minimal  
- Simplified **`GameManager`** — now focused purely on game state and story flow  
- Improved architecture separation

### User Interface
- Replaced `JTextArea` with **`JTextPane`** in `ConsoleWindow` for advanced text styling  
- Added support for **colored and animated text rendering** (`typeColored`, `printlnColored`)  
- Implemented **rounded window design** with dark modern aesthetic  

### Rendering & Presentation
- Enhanced **RenderManager** with visual-novel formatting:
  - **Narrative** blocks: gray separator lines and smoother pacing  
  - **Dialogue**: speaker name highlighting and clean spacing  
  - **Monologue**: redesigned into “thought bubbles” (❝ ❞)  
  - **Choice menus**: improved readability and consistent spacing  
- Added `[Press Enter to continue...]` prompt with subdued gray color  

### Technical / Maintenance
- General code cleanup and consistent naming conventions  
- Improved separation of concerns for easier future extensions  
- Prepared foundation for upcoming menu, save/load, and toolbar systems  

---

## v0.1 (Initial Public Milestone)
- Introduced **block-based architecture** (`Narrative`, `Dialogue`, `Monologue`, `Choice`)  
- Implemented **Scene container structure**  
- Added **game loop** (`render` / `input` / `update`)  
- Integrated **FancyConsoleWindow** renderer  
- Added **typing animation** for text  
- Implemented **enter-to-continue flow**
