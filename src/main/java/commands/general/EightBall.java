package commands.general;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EightBall extends Command {

    private List<String> choices = Arrays.asList("It is certain.", "It is decidedly so.", "Without a doubt.",
            "Yes- definitely.", "You may rely on it.", "As I see it, yes", "Most likely", "Outlook good.",
            "Yes.", "Signs point to yes.", "Reply hazy, try again.", "Ask again later.",
            "Better not tell you now", "Cannot predict now.", "Concentrate and ask again.",
            "Don't count on it.", "My reply is no.", "My sources say no.", "Outlook not so good.",
            "Very doubtful.");

    public EightBall () {
        this.name = "eightball";
        this.help = "Shake the magic Eight-Ball and peer into the future.";
        this.guildOnly = false;
        this.arguments = "[question]";
        this.botPermissions = new Permission[] {Permission.MESSAGE_EMBED_LINKS};
        this.aliases = new String[] {"8ball"};
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getArgs().isEmpty()) {
            Random rng = new Random();
            int choice = rng.nextInt(20);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            int color = choice <= 14 ? (choice <= 9 ? 0x28A745 : 0xFFC107) : 0xDC3545;
            embedBuilder.setColor(color);
            embedBuilder.setAuthor(event.getAuthor().getName(), null, event.getAuthor().getEffectiveAvatarUrl());
            embedBuilder.setDescription(event.getArgs());
            embedBuilder.addField("\uD83C\uDFB1Eightball\uD83C\uDFB1", choices.get(choice), true);
            event.reply(embedBuilder.build());

        } else {
            event.reactError();
        }
    }
}
