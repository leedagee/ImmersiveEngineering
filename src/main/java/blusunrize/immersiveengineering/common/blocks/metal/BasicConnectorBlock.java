/*
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package blusunrize.immersiveengineering.common.blocks.metal;

import blusunrize.immersiveengineering.api.IEProperties;
import blusunrize.immersiveengineering.api.wires.IImmersiveConnectable;
import blusunrize.immersiveengineering.common.blocks.generic.MiscConnectableBlock;
import blusunrize.immersiveengineering.common.register.IEBlocks.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fmllegacy.RegistryObject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Locale;

public class BasicConnectorBlock<T extends BlockEntity & IImmersiveConnectable> extends MiscConnectableBlock<T>
{
	public BasicConnectorBlock(Properties props, RegistryObject<BlockEntityType<T>> type)
	{
		super(props, type);
	}

	public static BlockEntry<BasicConnectorBlock<?>> forPower(String voltage, boolean relay)
	{
		return new BlockEntry<>(
				"connector_"+voltage.toLowerCase(Locale.US)+(relay?"_relay": ""), PROPERTIES,
				p -> new BasicConnectorBlock<>(p, EnergyConnectorTileEntity.SPEC_TO_TYPE.get(Pair.of(voltage, relay)))
		);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
		builder.add(IEProperties.FACING_ALL, BlockStateProperties.WATERLOGGED);
	}
}
