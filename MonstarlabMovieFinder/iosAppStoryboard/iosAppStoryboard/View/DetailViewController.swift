//
//  DetailViewController.swift
//  iosAppStoryboard
//
//  Created by juan sebastian gomez on 24/01/22.
//

import UIKit
import shared

class DetailViewController : UIViewController {
    
    
    @IBOutlet weak var posterView: UIImageView!
    
    @IBOutlet weak var overviewLabel: UITextView!
    
    var object: KmmMovie? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if object !=  nil {
            posterView.loadImageFromURL(urlString:self.object!.completePosterPath)
            self.overviewLabel.text = self.object!.overview
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
    }
    
    
}



