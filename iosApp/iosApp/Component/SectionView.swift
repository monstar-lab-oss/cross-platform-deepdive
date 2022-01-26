import SwiftUI

struct SectionView<Content>: View where Content: View {
    var title: String
    @ViewBuilder var content: () -> Content

    var body: some View {
        VStack(alignment: .leading) {
            Text(title)
                    .font(.largeTitle)
                    .padding(.bottom, 12)
            content()
        }
    }
}