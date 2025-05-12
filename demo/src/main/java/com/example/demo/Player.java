// Web 版 Player.java
package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int hp;
    private int attack;
    private int killCount = 0;
    private int totalDamage = 0;
    private List<Skill> skills = new ArrayList<>();

    public Player(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        // 預設學會火球術
        this.skills.add(new FireballSkill());
    }

    public boolean isAlive() { return hp > 0; }
    public void takeDamage(int dmg) { hp -= dmg; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public void addKill() { killCount++; }
    public void addDamage(int dmg) { totalDamage += dmg; }
    public void heal(int amount) {
        this.hp += amount;
    }
    public List<Skill> getSkills() { return skills; }

    public String getHealthBarHtml() {
        int totalBars = 20;
        int filledBars = Math.max(0, Math.min(totalBars, (int)((double) hp / 100 * totalBars)));

        StringBuilder bar = new StringBuilder();
        String color;
        double hpRatio = (double) hp / 100 * 100;
        if (hpRatio > 70) color = "green";
        else if (hpRatio > 30) color = "orange";
        else color = "red";

        bar.append("<div style='font-family: monospace;'>❤️ HP：<span style='color:")
           .append(color)
           .append("'>[");
        for (int i = 0; i < totalBars; i++) {
            bar.append(i < filledBars ? "█" : "░");
        }
        bar.append("]</span> ")
           .append(hp)
           .append(" / 100</div>");

        return bar.toString();
    }

    public String getSummaryHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>📊 【戰鬥結算】</h2>");
        sb.append("<ul>");
        sb.append("<li>玩家名稱：").append(name).append("</li>");
        sb.append("<li>總擊殺數：").append(killCount).append("</li>");
        sb.append("<li>累計傷害：").append(totalDamage).append("</li>");
        sb.append("<li>已學會技能：<ul>");
        for (Skill s : skills) {
            sb.append("<li>").append(s.getName()).append("</li>");
        }
        sb.append("</ul></li>");
        sb.append("</ul>");
        return sb.toString();
    }
} 