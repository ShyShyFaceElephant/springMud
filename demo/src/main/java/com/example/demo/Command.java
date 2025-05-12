package com.example.demo;
public interface Command {
    String execute(Player player, GameContext context);
}
