import SwiftUI
import shared

@main
struct iOSApp: App {
    private let di = PlatformDI.init()

    var body: some Scene {
        WindowGroup {
            MoviesScreenView(viewModel: .init(actionManager: di.provideMoviesUserActionManager()))
        }
    }
}
