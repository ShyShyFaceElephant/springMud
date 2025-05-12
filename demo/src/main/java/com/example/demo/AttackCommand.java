package com.example.demo;
public class AttackCommand implements Command {
    @Override
    public String execute(Player p, GameContext c) {
        StringBuilder sb = new StringBuilder();
        Monster m = c.getCurrentRoom().getMonster();

        if (m == null || !m.isAlive()) {
            sb.append("<p>這裡沒有可以攻擊的怪物。</p>");
            return sb.toString();
        }

        int damage = p.getAttack();
        m.takeDamage(damage);
        p.addDamage(damage);
        sb.append("<p>你攻擊了 ").append(m.getName())
          .append("，造成 <strong>").append(damage).append("</strong> 傷害。</p>");

        if (!m.isAlive()) {
            sb.append("<p>你擊敗了 ").append(m.getName()).append("！</p>");
            p.addKill();
        } else {
            int counter = m.getAttack();
            p.takeDamage(counter);
            sb.append("<p>").append(m.getName())
              .append(" 反擊你，造成 <strong>").append(counter).append("</strong> 傷害。</p>");
        }

        return sb.toString();
    }
}
