package com.frogniche.nichesmobs.player_animations;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.IdentityHashMap;
import java.util.Map;

import static com.frogniche.nichesmobs.NichesMobs.MOD_ID;

/**
 * This is an example implementation of PlayerAnimator resourceLoading and playerMapping
 */
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayerAnimatorExample {

    /**
     * This will map player objects and the animation containers. To retrieve the animation for the player, you'll need that exact player object.
     */
    public static final Map<AbstractClientPlayer, ModifierLayer<IAnimation>> animationData = new IdentityHashMap<>();


    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        //Set the player construct callback. It can be a lambda function.
        PlayerAnimationAccess.REGISTER_ANIMATION_EVENT.register(PlayerAnimatorExample::registerPlayerAnimation);
    }

    //This method will set your mods animation into the library.
    private static void registerPlayerAnimation(AbstractClientPlayer player, AnimationStack stack) {
        //This will be invoked for every new player
        var layer = new ModifierLayer<>();
        stack.addAnimLayer(1000, layer); //Register the layer with a priority
        //The priority will tell, how important is this animation compared to other mods. Higher number means higher priority
        //Mods with higher priority will override the lower priority mods (if they want to animation anything)

        //If you want to map with Players, you have to use IdentityHashMap. that doesn't require hashCode function but does require the exact same object.
        PlayerAnimatorExample.animationData.put(player, layer);

        //Alternative solution is to Mixin the mod animation container into the player class. But that requires knowing how to use Mixins.
    }

}