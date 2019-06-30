package com.mushroom.midnight.common.biome;

import com.mushroom.midnight.common.block.BladeshroomBlock;
import com.mushroom.midnight.common.registry.MidnightBlocks;
import com.mushroom.midnight.common.registry.MidnightEntities;
import com.mushroom.midnight.common.registry.MidnightFeatures;
import com.mushroom.midnight.common.registry.MidnightPlacements;
import com.mushroom.midnight.common.world.feature.config.CrystalClusterConfig;
import com.mushroom.midnight.common.world.feature.config.UniformCompositionConfig;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.TwoFeatureChoiceConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.HeightWithChanceConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class MidnightBiomeConfigurator {
    public static void addGlobalFeatures(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(
                MidnightFeatures.HEAP, new UniformCompositionConfig(MidnightBlocks.ROCKSHROOM.getDefaultState()),
                Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(100)
        ));

        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.BUSH, new BushConfig(MidnightBlocks.GHOST_PLANT.getDefaultState()),
                Placement.COUNT_CHANCE_HEIGHTMAP_DOUBLE, new HeightWithChanceConfig(4, 0.3F)
        ));

        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.BUSH, new BushConfig(MidnightBlocks.DRAGON_NEST.getDefaultState()),
                MidnightPlacements.DRAGON_NEST, IPlacementConfig.NO_PLACEMENT_CONFIG
        ));

        // TODO
//      .withFeature(FeatureSorting.LAST, UNSTABLE_BUSH_FEATURE, new ParcelPlacementConfig(6, 10, 0.2f))
//      .withFeature(FeatureSorting.LAST, UNDERGROUND_FEATURES, new UndergroundPlacementConfig(1, 3, 10, 50))
    }

    public static void addLumen(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.BUSH, new BushConfig(MidnightBlocks.LUMEN_BUD.getDefaultState()),
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)
        ));

        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.DOUBLE_PLANT, new DoublePlantConfig(MidnightBlocks.DOUBLE_LUMEN_BUD.getDefaultState()),
                Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(3)
        ));
    }

    public static void addSmallFungis(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.FUNGI_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)
        ));
    }

    public static void addDenseSmallFungis(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.FUNGI_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(6)
        ));
    }

    public static void addSmallBogFungis(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.BOG_FUNGI_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)
        ));
    }

    public static void addTallFungis(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.TALL_FUNGI, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(6)
        ));
    }

    public static void addTallBogFungis(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.TALL_BOG_FUNGI, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(6)
        ));
    }

    public static void addBulbFungi(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.BULB_FUNGI_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(7)
        ));

        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.LARGE_BULB_FUNGUS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(3)
        ));
    }

    public static void addCrystalFlowers(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.CRYSTAL_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(8)
        ));
    }

    public static void addCrystalClusters(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(
                MidnightFeatures.CRYSTAL_CLUSTER, new CrystalClusterConfig(MidnightBlocks.BLOOMCRYSTAL_ROCK.getDefaultState(), MidnightBlocks.BLOOMCRYSTAL.getDefaultState()),
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(3)
        ));

        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(
                MidnightFeatures.CRYSTAL_SPIRE, new CrystalClusterConfig(MidnightBlocks.BLOOMCRYSTAL_ROCK.getDefaultState(), MidnightBlocks.BLOOMCRYSTAL.getDefaultState()),
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(2)
        ));
    }

    public static void addRouxeClusters(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(
                MidnightFeatures.CRYSTAL_CLUSTER, new CrystalClusterConfig(MidnightBlocks.ROUXE_ROCK.getDefaultState(), MidnightBlocks.ROUXE.getDefaultState()),
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(5)
        ));
    }

    public static void addGrasses(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.GRASS, new GrassFeatureConfig(MidnightBlocks.GRASS.getDefaultState()),
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(6)
        ));

        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.DOUBLE_PLANT, new DoublePlantConfig(MidnightBlocks.TALL_GRASS.getDefaultState()),
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(3)
        ));
    }

    public static void addFingeredGrass(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.GRASS, new GrassFeatureConfig(MidnightBlocks.FINGERED_GRASS.getDefaultState()),
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)
        ));
    }

    public static void addTrenchstoneBoulders(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(
                MidnightFeatures.TRENCHSTONE_BOULDER, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.CHANCE_HEIGHTMAP, new ChanceConfig(3)
        ));
    }

    public static void addNightstoneSpikesAndBoulders(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(
                Feature.RANDOM_BOOLEAN_SELECTOR, new TwoFeatureChoiceConfig(
                        MidnightFeatures.NIGHTSTONE_BOULDER, IFeatureConfig.NO_FEATURE_CONFIG,
                        MidnightFeatures.SPIKE, new UniformCompositionConfig(MidnightBlocks.NIGHTSHROOM.getDefaultState())
                ),
                Placement.CHANCE_HEIGHTMAP, new ChanceConfig(4)
        ));
    }

    public static void addSparseShadowrootTrees(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.RANDOM_BOOLEAN_SELECTOR, new TwoFeatureChoiceConfig(
                        MidnightFeatures.SHADOWROOT_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
                        MidnightFeatures.DEAD_TREE, IFeatureConfig.NO_FEATURE_CONFIG
                ),
                Placement.CHANCE_HEIGHTMAP, new ChanceConfig(5)
        ));
    }

    public static void addSparseSuavis(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.SUAVIS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.CHANCE_HEIGHTMAP_DOUBLE, new ChanceConfig(32)
        ));
    }

    public static void addCommonSuavis(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.SUAVIS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)
        ));
    }

    public static void addSparseDeadTrees(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.DEAD_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.CHANCE_HEIGHTMAP, new ChanceConfig(6)
        ));
    }

    public static void addVioleafs(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.BUSH, new BushConfig(MidnightBlocks.VIOLEAF.getDefaultState()),
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(2)
        ));
    }

    public static void addRunebushes(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.GRASS, new GrassFeatureConfig(MidnightBlocks.RUNEBUSH.getDefaultState()),
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(32)
        ));
    }

    public static void addBogweed(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.BOGWEED_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(1)
        ));
    }

    public static void addDeadLogs(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.DEAD_LOG, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(5)
        ));
    }

    public static void addVigilantForestTrees(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.RANDOM_BOOLEAN_SELECTOR, new TwoFeatureChoiceConfig(
                        MidnightFeatures.SHADOWROOT_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
                        MidnightFeatures.DARK_WILLOW_TREE, IFeatureConfig.NO_FEATURE_CONFIG
                ),
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(8)
        ));
    }

    public static void addBogTrees(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.RANDOM_BOOLEAN_SELECTOR, new TwoFeatureChoiceConfig(
                        MidnightFeatures.SHADOWROOT_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
                        MidnightFeatures.DARK_WILLOW_TREE, IFeatureConfig.NO_FEATURE_CONFIG
                ),
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(4)
        ));
    }

    public static void addBogDeadTrees(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.BOG_DEAD_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.CHANCE_HEIGHTMAP, new ChanceConfig(3)
        ));
    }

    public static void addLargeBogshrooms(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.LARGE_BOGSHROOM, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP, new FrequencyConfig(1)
        ));
    }

    public static void addLargeFungis(ConfigurableBiome biome) {
        // TODO
//       .withFeature(LARGE_FUNGI_FEATURES, new SurfacePlacementConfig(6))
    }

    public static void addBladeshrooms(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                Feature.BUSH, new BushConfig(MidnightBlocks.BLADESHROOM.getDefaultState().with(BladeshroomBlock.STAGE, BladeshroomBlock.Stage.CAPPED)),
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)
        ));
    }

    public static void addAlgaeAndMoss(ConfigurableBiome biome) {
        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.DECEITFUL_ALGAE, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(10)
        ));

        biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(
                MidnightFeatures.DECEITFUL_MOSS, IFeatureConfig.NO_FEATURE_CONFIG,
                Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(16)
        ));
    }

    public static void addStandardCreatureSpawns(ConfigurableBiome biome) {
        addCreature(biome, MidnightEntities.NIGHTSTAG, 100, 1, 3);
    }

    public static void addStandardMonsterSpawns(ConfigurableBiome biome) {
        addMonster(biome, MidnightEntities.RIFTER, 100, 1, 2);
        addMonster(biome, EntityType.ENDERMAN, 10, 4, 4);
    }

    public static void addRockySpawns(ConfigurableBiome biome) {
        addMonster(biome, MidnightEntities.HUNTER, 5, 1, 2);
    }

    public static void addUndergroundSpawns(ConfigurableBiome biome) {
        addMonster(biome, MidnightEntities.STINGER, 100, 2, 4);
        addMonster(biome, EntityType.ENDERMAN, 10, 4, 4);
    }

    public static void addForestSpawns(ConfigurableBiome biome) {
        addMonster(biome, MidnightEntities.SKULK, 100, 1, 2);
    }

    private static void addCreature(ConfigurableBiome biome, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
        biome.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(type, weight, minGroupSize, maxGroupSize));
    }

    private static void addMonster(ConfigurableBiome biome, EntityType<?> type, int weight, int minGroupSize, int maxGroupSize) {
        biome.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(type, weight, minGroupSize, maxGroupSize));
    }
}