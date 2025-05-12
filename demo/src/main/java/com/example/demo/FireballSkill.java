package com.example.demo;
public class FireballSkill implements Skill {
    @Override
    public String getName() {
        return "火球術";
    }

    @Override
    public String use(Player p, Monster m) {
        StringBuilder result = new StringBuilder();
        int damage = p.getAttack() + 10;
        m.takeDamage(damage);
        p.addDamage(damage);

        result.append("<p>🔥 火球擊中 ")
              .append(m.getName())
              .append("，造成 <strong>")
              .append(damage)
              .append("</strong> 傷害！</p>");

        if (!m.isAlive()) {
            result.append("<p>")
                  .append(m.getName())
                  .append(" 被燒成灰燼！</p>");
            p.addKill();
        } else {
            int counter = m.getAttack();
            p.takeDamage(counter);
            result.append("<p>")
                  .append(m.getName())
                  .append(" 反擊你，造成 <strong>")
                  .append(counter)
                  .append("</strong> 傷害。</p>");
        }

        return result.toString();
    }
}
