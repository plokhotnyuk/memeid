/*
 * Copyright 2019-2020 47 Degrees Open Source <https://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package memeid.kotlin

import memeid.kotlin.node.Node
import memeid.kotlin.time.time
import memeid.UUID
import memeid.kotlin.time.Posix

class UUID(val uuid: UUID) {

  /** The most significant 64 bits of this UUID's 128 bit value */
  val msb: Long = uuid.mostSignificantBits

  /** The least significant 64 bits of this UUID's 128 bit value */
  val lsb: Long = uuid.leastSignificantBits

  object V1 {
    fun next(node: Node): UUID =  UUID.V1.next(node.node) { time.monotonic }
  }

  object V3 {
    /**
     * Construct a namespace name-based v3 UUID. Uses MD5 as a hash algorithm
     *
     * @param namespace [UUID] used for the [V3] generation
     * @tparam A Sets the type for the [Digestible] parameter
     * @return [V3]
     */
    inline fun <reified A> apply(namespace: UUID): UUID =
      UUID.V3.from(namespace, A::class.java, Digestible::invoke)
  }

  object V4 {

    /**
     * Construct a v4 (random) UUID from the given `msb` and `lsb`.
     *
     * @param msb Most significant bit in [Long] representation
     * @param lsb Least significant bit in [Long] representation
     * @return [V4]
     */
    fun apply(msb: Long, lsb: Long): UUID = UUID.V4.from(msb, lsb)

    // Construct a v4 (random) UUID.
    fun random(): UUID = UUID.V4.random()

    // Construct a SQUUID (random, time-based) UUID.
    fun squuid(p: Posix): UUID = UUID.V4.squuid(p.value)
  }

  object V5 {

    /**
     * Construct a namespace name-based v5 UUID. Uses SHA as a hash algorithm
     *
     * @param namespace [UUID] used for the [V5] generation
     * @tparam A Sets the type for the [Digestible] parameter
     * @return [V5]
     */
    inline fun <reified A> apply(namespace: UUID): UUID =
      UUID.V5.from(namespace, A::class.java, Digestible::invoke)
  }
}


