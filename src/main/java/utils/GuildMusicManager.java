package utils;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.core.entities.Guild;

class GuildMusicManager {
    final AudioPlayer player;
    final TrackScheduler scheduler;

    GuildMusicManager(AudioPlayerManager manager, Guild guild) {
        player = manager.createPlayer();
        scheduler = new TrackScheduler(player, guild);
        player.addListener(scheduler);
    }

    AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(player);
    }
}