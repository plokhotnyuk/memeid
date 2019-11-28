package memeid

import java.util.{UUID => JUUID}

/**
 * A variety of decorators that enable converting between
 * Scala and Java UUIDs using extension methods, `asScala` and `asJava`.
 *
 * The extension methods return adapters for the corresponding API.
 *
 * The following conversions are supported via `asScala` and `asJava`:
 *
 * {{{
 *  memeid.UUID       <=> java.util.UUID
 * }}}
 */
object JavaConverters {

  implicit final class JUUIDAsScala(private val juuid: JUUID) extends AnyVal {

    /** Converts this `java.util.UUID` into a `memeid.UUID` */
    def asScala: UUID = new UUID(juuid)

  }

  implicit final class UUIDAsJava(private val uuid: UUID) extends AnyVal {

    /** Converts this `memeid.UUID` into a `java.util.UUID` */
    def asJava: JUUID = uuid.juuid

  }

}
