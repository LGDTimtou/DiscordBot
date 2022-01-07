package com.lgdtimtou;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Random;

public class ChatEvent extends ListenerAdapter {
    private Random rn;
    private HashMap<String,Integer> hl;
    private HashMap<String,Integer> streak;


    public ChatEvent(){
        rn = new Random();
        hl = new HashMap<>();
        streak = new HashMap<>();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        if (e.getAuthor().isBot())
            return;
        String name = e.getMember().getEffectiveName();
        String message = e.getMessage().getContentRaw();
        EmbedBuilder eb = new EmbedBuilder();
        if (message.contains("!record")){
            eb.setColor(Color.GRAY);
            eb.addField("Record", "**" + NoSQL.getRecord() + "**", false);
            e.getChannel().sendMessageEmbeds(eb.build()).queue();
            return;
        }
        if (message.contains("!stinkt")){
            eb.setColor(Color.red);
            eb.setTitle("Stinkt");
            if (!name.contains("Timon")){
                eb.setDescription("Ja je stinkt idd, " + name);
            } else {
                eb.setDescription("I love you Timon <3");
            }
            e.getChannel().sendMessageEmbeds(eb.build()).queue();
            return;
        }
        if (message.contains("hihihiha")){
            e.getMessage().delete().queue();
            File file = new File("C:\\Users\\loepi\\OneDrive\\Afbeeldingen\\hihihiha.gif");
            e.getChannel().sendMessage("HIHIHIHA").addFile(file).queue();
            return;
        }
        if (message.contains("!gay")){
            int gaylevel = rn.nextInt(11);
            e.getChannel().sendMessage(name + " zen gaylevel is een " + gaylevel + "/10").queue();
            if (gaylevel >= 8){
                e.getChannel().sendMessage("HOMOOOO").queue();
            }
            return;
        }
        if (message.contains("!starthl")){
            eb.setColor(Color.GRAY);
            eb.setTitle("HogerLager - " + name);
            if (hl.get(name) != null){
                eb.setDescription("Je zit al in een potje " + name +
                        ", facking DOWNIE\n Gebruik !hoger of !lager \nje vorig nummer was " + hl.get(name));
            } else {
                int getal = rn.nextInt(13)+1;
                hl.put(name, getal);
                streak.put(name, 0);
                eb.setDescription("Je startgetal is **" + getal + "**\n!hoger of !lager?");
                eb.addField("Record","Het record is op dit moment **" + NoSQL.getRecord() + "**", false);
            }
            e.getChannel().sendMessageEmbeds(eb.build()).queue();
            return;
        }
        if (message.contains("!hoger") || message.contains("!lager")){
            eb.setTitle("Hoger Lager - " + name);
            if (hl.get(name) == null){
                eb.setDescription("Je zit nog niet in een potje " + name + ", facking DOWNIE"
                    + "\nGebruik eerst !starthl");
            } else {
                int record = NoSQL.getRecord();
                boolean recordv = false;
                int nieuwgetal = rn.nextInt(13) +1;
                int getal = hl.get(name);
                if (record <= streak.get(name)) {
                    NoSQL.setRecord(streak.get(name));
                    recordv = true;
                }
                if ((getal < nieuwgetal && message.contains("!lager"))
                        ||  (getal > nieuwgetal && message.contains("!hoger"))){
                    eb.setDescription("HAHAHAHAHA je nieuw getal is **" + nieuwgetal + "**"
                        + "\nJe was dus fout, jammer voor je\n");
                    eb.addField("Streak", "Je streak was " + streak.get(name), false);
                    if (recordv){
                        eb.addField("Record", "Wel lekker want jad et record verbroken\nHet record is nu **" +streak.get(name) + "**", false);
                    }
                    eb.setColor(Color.red);
                    hl.remove(name);
                    streak.remove(name);
                } else {
                    streak.put(name, streak.get(name) + 1);
                    eb.setColor(Color.green);
                    eb.setDescription("Lekker pik je nieuw getal is **" + nieuwgetal + "**\n");
                    eb.addField("Streak", "Je streak is nu " + streak.get(name), false);
                    if (recordv){
                        eb.addField("Record", "Lekker man jebt et record verbroken", false);
                    }

                    hl.put(name, nieuwgetal);
                }
            }
            e.getChannel().sendMessageEmbeds(eb.build()).queue();
            return;
        }
    }

}
