package commands.general;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;


public class Avatar extends Command {

    public Avatar () {
        this.name = "avatar";
        this.help = "Gets the specified or current user's avatar.";
        this.guildOnly = false;
        this.arguments = "[user]";
        this.botPermissions = new Permission[] {Permission.MESSAGE_EMBED_LINKS};

    }

    @Override
    protected void execute(CommandEvent event) {
        if (!event.getArgs().isEmpty()) {
            if (!event.getMessage().getMentionedUsers().isEmpty()){
                Member target = event.getMessage().getMentionedMembers().get(0);
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(target.getColor());
                embedBuilder.setImage(target.getUser().getEffectiveAvatarUrl());
                event.reply(embedBuilder.build());
            } else {
                try {
                    Member member = event.getGuild().getMembersByEffectiveName(event.getArgs(), true).get(0);
                    EmbedBuilder embedBuilder = new EmbedBuilder();
                    embedBuilder.setColor(member.getColor());
                    embedBuilder.setImage(member.getUser().getEffectiveAvatarUrl());
                    event.reply(embedBuilder.build());
                } catch (IndexOutOfBoundsException e) {
                    event.reactError();
                }

            }
        } else {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setColor(event.getGuild().getMember(event.getAuthor()).getColor());
            embedBuilder.setImage(event.getAuthor().getEffectiveAvatarUrl());
            event.reply(embedBuilder.build());
        }
    }
}
