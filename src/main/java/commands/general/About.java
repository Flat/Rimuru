package commands.general;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.bot.entities.ApplicationInfo;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class About extends Command {

    public About() {
        this.name = "about";
        this.help = "Shows useful information about the bot";
        this.guildOnly = false;
        this.botPermissions = new Permission[] {Permission.MESSAGE_EMBED_LINKS};
    }

    @Override
    protected void execute(CommandEvent event) {
        String inviteUrl;
        try {
            ApplicationInfo info = event.getJDA().asBot().getApplicationInfo().complete();
            inviteUrl = info.isBotPublic() ? info.getInviteUrl(Permission.MESSAGE_WRITE, Permission.MESSAGE_READ,
                    Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES,
                    Permission.VOICE_CONNECT, Permission.VOICE_SPEAK, Permission.VIEW_CHANNEL) : "";
        } catch (Exception e) {
            Logger log = LoggerFactory.getLogger(this.getClass());
            log.info("Unable to get invite url.");
            inviteUrl = "'";
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(0x99DBFE);
        embedBuilder.setAuthor(event.getSelfUser().getName(), "https://github.com/Flat/Rimuru",
                event.getSelfUser().getAvatarUrl());

        StringBuilder description = new StringBuilder();
        description.append("Hello I'm ").append(event.getSelfUser().getName()).append("! ")
                .append(!inviteUrl.isEmpty() ? "You can [`invite`](" + inviteUrl + ") me to your own server! " : "")
                .append("I was written in Java by ").append(event.getJDA().getUserById(event.getClient()
                .getOwnerId()) == null ? "<@" + event.getClient().getOwnerId() + ">" : event.getJDA()
                .getUserById(event.getClient().getOwnerId()).getName()).append("\nYou can type `")
                .append(event.getClient().getTextualPrefix()).append(event.getClient().getHelpWord())
                .append("` to see available commands.");

        embedBuilder.setDescription(description);

        if (event.getJDA().getShardInfo() == null) {
            embedBuilder.addField("Servers", Integer.toString(event.getJDA().getGuilds().size()), true);
            embedBuilder.addField("Users", Integer.toString(event.getJDA().getUsers().size()), true);
        } else {
            embedBuilder.addField("Servers", Integer.toString(event.getClient().getTotalGuilds()), true);
            embedBuilder.addField("Shards", Integer.toString(event.getJDA().getShardInfo().getShardTotal()), true);
            embedBuilder.addField("Users", Integer.toString(event.getJDA().getUsers().size()), true);
        }

        embedBuilder.setFooter("Running since", null);
        embedBuilder.setTimestamp(event.getClient().getStartTime());
        event.reply(embedBuilder.build());

    }
}
