import SwiftUI

struct  ErrorView: View {
    var message: String

    var body: some View {
        Text("An error has occurred displaying: \(message)")
    }
}