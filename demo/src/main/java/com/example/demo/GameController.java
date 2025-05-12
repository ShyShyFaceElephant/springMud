// Web 版 GameController.java
package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameContext context;
    private final List<Room> allRooms;

    public GameController(GameContext context) {
        this.context = context;
        this.allRooms = discoverAllRooms(context.getCurrentRoom());
    }

    @GetMapping("/command")
    public String handleCommand(@RequestParam String input) {
        StringBuilder output = new StringBuilder();
        Player player = context.getPlayer();

        if (!player.isAlive()) {
            output.append("<p>你已死亡，遊戲結束。</p>");
            output.append("<pre>").append(player.getSummaryHtml()).append("</pre>");
            return output.toString();
        }

        if (allMonstersDefeated()) {
            output.append("<p>🎉 恭喜！你擊敗了所有怪物，贏得勝利！</p>");
            output.append("<pre>").append(player.getSummaryHtml()).append("</pre>");
            return output.toString();
        }

        // 執行指令
        if (input.equalsIgnoreCase("exit")) {
            output.append("<p>你選擇離開遊戲，謝謝遊玩！</p>");
            output.append("<pre>").append(player.getSummaryHtml()).append("</pre>");
            return output.toString();
        }

        Command command = CommandParser.parse(input.toLowerCase());
        if (command != null) {
            String result = command.execute(player, context);
            output.append(result);
        } else {
            output.append("<p>❌ 無效的指令。</p>");
        }

        // 更新狀態畫面
        Room room = context.getCurrentRoom();
        output.append("<h3>你目前在：").append(room.getName()).append("</h3>");
        output.append(printPlayerHpHtml(player));
        if (room.getMonster() != null && room.getMonster().isAlive()) {
            output.append(printMonsterHpHtml(room.getMonster()));
        }
        output.append("<p>可用方向：").append(room.getExitString()).append("</p>");
        output.append("<p>可用指令：look | move <方向> | attack | skill fireball | use potion | exit</p>");

        return output.toString();
    }

    private boolean allMonstersDefeated() {
        return allRooms.stream().allMatch(r -> r.getMonster() == null || !r.getMonster().isAlive());
    }

    private List<Room> discoverAllRooms(Room start) {
        List<Room> rooms = new ArrayList<>();
        Set<Room> visited = new HashSet<>();
        Queue<Room> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Room current = queue.poll();
            if (visited.contains(current)) continue;
            visited.add(current);
            rooms.add(current);
            for (String dir : current.getExitString().split(",\\s*")) {
                Room next = current.getExit(dir);
                if (next != null && !visited.contains(next)) {
                    queue.add(next);
                }
            }
        }
        return rooms;
    }

    private String printPlayerHpHtml(Player p) {
        int hp = p.getHp();
        return String.format("<p>❤️ HP：%d / 100</p>", hp);
    }

    private String printMonsterHpHtml(Monster m) {
        return String.format("<p>👾 %s HP：%d / %d</p>", m.getName(), m.getHp(), m.getMaxHp());
    }
}