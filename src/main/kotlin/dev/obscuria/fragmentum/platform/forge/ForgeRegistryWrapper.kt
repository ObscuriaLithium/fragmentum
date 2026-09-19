package dev.obscuria.fragmentum.platform.forge

//? forge {
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.*
import net.minecraft.core.*
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.util.RandomSource
import net.minecraftforge.registries.IForgeRegistry
import org.apache.commons.lang3.mutable.Mutable
import org.apache.commons.lang3.mutable.MutableObject
import org.jetbrains.annotations.ApiStatus
import java.util.*
import java.util.function.Function
import java.util.stream.Stream

@ApiStatus.Internal
internal class ForgeRegistryWrapper<T>(
	registryKey: ResourceKey<Registry<T>>
) : Registry<T> where T : Any {

	private val key: ResourceKey<Registry<T>>
	private val byNameCodec: Codec<T>
	private val holderByNameCodec: Codec<Holder<T>>
	private val source: Mutable<IForgeRegistry<T>>

	init {
		this.holderByNameCodec = createHolderByNameCodec()
		this.byNameCodec = createByNameCodec()
		this.source = MutableObject()
		this.key = registryKey
	}

	fun bind(registry: IForgeRegistry<T>) {
		this.source.value = registry
	}

	override fun key(): ResourceKey<Registry<T>> {
		return key
	}

	override fun byNameCodec(): Codec<T> {
		return byNameCodec
	}

	override fun holderByNameCodec(): Codec<Holder<T>> {
		return holderByNameCodec
	}

	override fun keySet(): MutableSet<ResourceLocation?> {
		return source.value.keys
	}

	override fun entrySet(): Set<Map.Entry<ResourceKey<T>, T>> {
		return source.value.entries
	}

	override fun registryKeySet(): Set<ResourceKey<T>> {
		return source.value.entries.map { it.key }.toSet()
	}

	override fun containsKey(id: ResourceLocation): Boolean {
		return source.value.containsKey(id)
	}

	override fun containsKey(resourceKey: ResourceKey<T>): Boolean {
		return source.value.containsKey(resourceKey.location())
	}

	override fun getKey(value: T): ResourceLocation? {
		return source.value.getKey(value)
	}

	override fun getResourceKey(value: T): Optional<ResourceKey<T>> {
		return source.value.getResourceKey(value)
	}

	override fun iterator(): MutableIterator<T> {
		return source.value.iterator()
	}

	override fun get(resourceKey: ResourceKey<T>?): T? {
		val delegate = source.value.getDelegate(resourceKey).orElse(null)
		return if (delegate != null && delegate.isBound) delegate.value() else null
	}

	override fun get(id: ResourceLocation?): T? {
		return source.value.getValue(id)
	}

	override fun freeze(): Registry<T> {
		throw UnsupportedOperationException()
	}

	override fun getRandom(randomSource: RandomSource): Optional<Holder.Reference<T>> {
		throw UnsupportedOperationException()
	}

	override fun createIntrusiveHolder(t: T): Holder.Reference<T> {
		throw UnsupportedOperationException()
	}

	override fun getHolder(i: Int): Optional<Holder.Reference<T>> {
		throw UnsupportedOperationException()
	}

	override fun getHolder(resourceKey: ResourceKey<T>): Optional<Holder.Reference<T>> {
		throw UnsupportedOperationException()
	}

	override fun wrapAsHolder(t: T): Holder<T> {
		throw UnsupportedOperationException()
	}

	override fun holders(): Stream<Holder.Reference<T>> {
		throw UnsupportedOperationException()
	}

	override fun getTag(tagKey: TagKey<T>): Optional<HolderSet.Named<T>> {
		throw UnsupportedOperationException()
	}

	override fun getOrCreateTag(tagKey: TagKey<T>): HolderSet.Named<T> {
		throw UnsupportedOperationException()
	}

	override fun getTags(): Stream<Pair<TagKey<T>, HolderSet.Named<T>>> {
		throw UnsupportedOperationException()
	}

	override fun getTagNames(): Stream<TagKey<T>> {
		throw UnsupportedOperationException()
	}

	override fun resetTags() {
		throw UnsupportedOperationException()
	}

	override fun bindTags(map: MutableMap<TagKey<T>, MutableList<Holder<T>>>) {
		throw UnsupportedOperationException()
	}

	override fun holderOwner(): HolderOwner<T> {
		throw UnsupportedOperationException()
	}

	override fun asLookup(): HolderLookup.RegistryLookup<T> {
		throw UnsupportedOperationException()
	}

	override fun lifecycle(t: T): Lifecycle {
		throw UnsupportedOperationException()
	}

	override fun registryLifecycle(): Lifecycle {
		throw UnsupportedOperationException()
	}

	override fun getId(t: T?): Int {
		throw UnsupportedOperationException()
	}

	override fun byId(i: Int): T? {
		throw UnsupportedOperationException()
	}

	override fun size(): Int {
		throw UnsupportedOperationException()
	}

	private fun createByNameCodec(): Codec<T> {
		return Codec.of(
			object : Encoder<T> {
				override fun <R> encode(input: T, ops: DynamicOps<R>, prefix: R): DataResult<R> {
					return source.value.getCodec().encode(input, ops, prefix)
				}
			},
			object : Decoder<T> {
				override fun <R> decode(ops: DynamicOps<R>, input: R): DataResult<Pair<T, R>> {
					return source.value.getCodec().decode(ops, input)
				}
			})
	}

	private fun createHolderByNameCodec(): Codec<Holder<T>> {
		return this.referenceHolderWithLifecycle().flatComapMap(Function.identity()) {
			this.safeCastToReference(it)
		}
	}

	private fun referenceHolderWithLifecycle(): Codec<Holder.Reference<T>> {
		return ResourceLocation.CODEC.comapFlatMap(this::tryGetReference) { reference ->
			reference.key().location()
		}
	}

	private fun tryGetReference(id: ResourceLocation): DataResult<Holder.Reference<T>> {
		return runCatching {
			getHolderOrThrow(ResourceKey.create(key(), id))
		}.fold(
			{ DataResult.success(it) },
			{ DataResult.error { "Unknown registry key in $key: $id" } })
	}

	private fun safeCastToReference(value: Holder<T>): DataResult<Holder.Reference<T>> {
		if (value is Holder.Reference<T>) return DataResult.success(value)
		return DataResult.error { "Unregistered holder in " + this.key() + ": " + value }
	}
}
//?}
