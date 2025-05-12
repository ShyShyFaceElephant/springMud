// Web 版 MoveCommand.java
package com.example.demo;

public class MoveCommand implements Command {
    private String direction;

    public MoveCommand(String direction) {
        this.direction = direction;
    }

    @Override
    public String execute(Player p, GameContext c) {
        Room nextRoom = c.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            c.setCurrentRoom(nextRoom);
            return "<p>你移動到了 <strong>" + nextRoom.getName() + "</strong>。</p>";
        } else {
            return "<p>❌ 那個方向沒有路。</p>";
        }
    }
} 