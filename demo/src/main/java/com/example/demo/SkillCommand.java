// Web 版 SkillCommand.java
package com.example.demo;

public class SkillCommand implements Command {
    private String skillName;

    public SkillCommand(String name) {
        this.skillName = name;
    }

    @Override
    public String execute(Player p, GameContext c) {
        Monster m = c.getCurrentRoom().getMonster();
        if (m == null || !m.isAlive()) {
            return "<p>這裡沒有可以使用技能攻擊的怪物。</p>";
        }

        // 目前僅支援火球術
        Skill skill = new FireballSkill();
        StringBuilder sb = new StringBuilder();
        sb.append("<p>你對 ").append(m.getName())
          .append(" 使用了技能：<strong>")
          .append(skill.getName()).append("</strong></p>");
        sb.append(skill.use(p, m));
        return sb.toString();
    }
}