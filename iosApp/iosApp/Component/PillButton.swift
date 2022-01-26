import SwiftUI

struct PillButton<Content>: View where Content: View {
    @ViewBuilder var content: () -> Content

    var body: some View {
        ZStack {
            content()
        }
        .padding(8)
        .background(.gray)
        .cornerRadius(8)
    }
}