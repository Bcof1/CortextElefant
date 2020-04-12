package com.noahhendrickson.elefant;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Noah Hendrickson on 4/11/2020
 */
public class CommandBundle {

    public static final String FOOTER = "For support, join https://discord.gg/vNg5RJS";

    private final List<String> args;

    private final User user;
    private final Member member;
    private final TextChannel channel;
    private final Guild guild;
    private final JDA jda;

    public CommandBundle(@NotNull GuildMessageReceivedEvent event) {
        List<String> argsWithCommand = Arrays.asList(event.getMessage().getContentRaw().split(" "));

        this.args = argsWithCommand.subList(1, argsWithCommand.size());
        this.user = event.getAuthor();
        this.member = event.getMember();
        this.channel = event.getChannel();
        this.guild = event.getGuild();
        this.jda = event.getJDA();
    }

    public boolean hasArgs() {
        return args.size() > 0;
    }

    public boolean areArgsEqualTo(int length) {
        return args.size() == length;
    }

    public boolean areArgsMoreThan(int moreThan) {
        return args.size() > moreThan;
    }

    public boolean areArgsLessThan(int lessThan) {
        return args.size() < lessThan;
    }

    public String getArgAt(int at) {
        if (at > args.size())
            throw new IllegalArgumentException("The index of " + at + " is larger than the argument array!");
        if (at < 0)
            throw new IllegalArgumentException("The index cannot be negated!");

        return args.get(at);
    }

    public void sendMessage(@NotNull Message... messages) {
        for (Message message : messages) channel.sendMessage(message).queue();
    }

    public void sendMessage(@NotNull String... messages) {
        for (String message : messages) channel.sendMessage(message).queue();
    }

    public void sendMessage(@NotNull EmbedBuilder... embeds) {
        for (EmbedBuilder embed : embeds) channel.sendMessage(embed.setFooter(FOOTER).build()).queue();
    }

    public List<String> getArgs() {
        return args;
    }

    public User getUser() {
        return user;
    }

    public Member getMember() {
        return member;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public Guild getGuild() {
        return guild;
    }

    public JDA getJDA() {
        return jda;
    }
}