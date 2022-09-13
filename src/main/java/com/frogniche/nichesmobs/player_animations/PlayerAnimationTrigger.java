package com.frogniche.nichesmobs.player_animations;
import com.frogniche.nichesmobs.NichesMobs;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Example, how to trigger animations on specific players
 * Always trigger animation on client-side.  Maybe as a response to a network packet or event
 */
@Mod.EventBusSubscriber(modid = NichesMobs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerAnimationTrigger {

    //We need to know when to play an animation
    //This can be anything depending on your ideas (see Emotecraft, BetterCombat ...)
    @SubscribeEvent
    public static void onChatReceived(ClientChatReceivedEvent event) {
        //Test if it is a player (main or other) and the message
        if (!event.getPlayerChatMessage().signer().isSystem() && event.getPlayerChatMessage().signedContent().plain().equals("waving")) {


            //Get the player from Minecraft, using the chat profile ID. From network packets, you'll receive entity IDs instead of UUIDs
            var player = Minecraft.getInstance().level.getPlayerByUUID(event.getPlayerChatMessage().signer().profileId());

            if (player == null) return; //The player can be null because it was a system message or because it is not loaded by this player.

            //Get the animation for that player
            var animation = PlayerAnimatorExample.animationData.get((AbstractClientPlayer) player);
            if (animation != null) {
                //You can set an animation from anywhere ON THE CLIENT
                //Do not attempt to do this on a server, that will only fail

                animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation("nichesmobs", "waving"))));
                //You might use  animation.replaceAnimationWithFade(); to create fade effect instead of sudden change
                //See javadoc for details
            }
        }
    }

    //For server-side animation playing, see Emotecraft API
}