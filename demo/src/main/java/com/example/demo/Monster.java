package com.example.demo;
public class Monster {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;

    public Monster(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;  // 記錄初始血量為最大血量
        this.attack = attack;
    }

    public String getName() { return name; }
    public boolean isAlive() { return hp > 0; }
    public void takeDamage(int damage) { hp -= damage; }
    public int getAttack() { return attack; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }  // 新增方法
}
