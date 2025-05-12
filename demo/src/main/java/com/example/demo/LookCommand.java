// Web 版 LookCommand.java
package com.example.demo;

public class LookCommand implements Command {
    @Override
    public String execute(Player p, GameContext c) {
        Room room = c.getCurrentRoom();
        StringBuilder sb = new StringBuilder();

        sb.append("<h3>【房間】：").append(room.getName()).append("</h3>");
        sb.append("<p>【描述】：").append(room.getDescription()).append("</p>");

        Monster monster = room.getMonster();
        if (monster != null && monster.isAlive()) {
            sb.append("<p>【怪物】：").append(monster.getName())
              .append("（HP: ").append(monster.getHp()).append("）</p>");
        }

        if (room.hasPotion()) {
            sb.append("<p>【道具】：治療藥水</p>");
        }

        return sb.toString();
    }
}