package com.lgdtimtou;

import com.lgdtimtou.ChatEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    public static JDABuilder jdaBuilder;
    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        jdaBuilder = JDABuilder.createDefault("OTE4OTg1MTYzODQ3OTE3NjU4.YbPNoA.dIJu4_7H-4v6tXLgi5syZeKwQaw");
        jdaBuilder.setActivity(Activity.playing("met zen lul"));
        jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda = jdaBuilder.build();
        jda.addEventListener(new ChatEvent());
        NoSQL.connect();
        System.out.println(NoSQL.getRecord());
    }


}
