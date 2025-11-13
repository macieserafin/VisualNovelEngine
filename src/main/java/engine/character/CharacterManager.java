package engine.character;

public class CharacterManager {
    private Character currentCharacter;

    public void createCharacter(String name) {
        this.currentCharacter = new Character(name);
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character character) {
        this.currentCharacter = character;
    }

    public boolean hasCharacter() {
        return currentCharacter != null;
    }

    public void clearCharacter() {
        this.currentCharacter = null;
    }

}
