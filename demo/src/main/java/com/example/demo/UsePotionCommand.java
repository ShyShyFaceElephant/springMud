// Web 版 UsePotionCommand.java
package com.example.demo;

public class UsePotionCommand implements Command {
    @Override
    public String execute(Player p, GameContext c) {
        Room r = c.getCurrentRoom();
        if (r.hasPotion()) {
            r.removePotion();
            p.heal(30); // 假設回血 30
            return "<p>你撿起並使用了藥水，恢復了 <strong>30</strong> 點生命值。</p>";
        } else {
            return "<p>這裡沒有藥水。請先 'look' 確認。</p>";
        }
    }
}
