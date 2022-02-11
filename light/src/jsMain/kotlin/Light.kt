import com.cliabhach.terrapin.nav.RoutesObject

/**
 * Public JS exports.
 *
 * For most typescript projects, we use ES Module definitions, where the
 * 'export as namespace' syntax isn't very useful. If you don't use the
 * right import syntax then you'll see errors.
 *
 * @author Philip Cohn-Cort
 */
@JsExport
val LightRoutes = object {
    val LHome = RoutesObject.Home
    val LSearch = RoutesObject.Search
}

@JsExport
val Something = "85"