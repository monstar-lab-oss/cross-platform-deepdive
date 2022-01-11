import SwiftUI
import shared

@main
struct iOSApp: App {
        
    private let di = IOsDI.init()
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
