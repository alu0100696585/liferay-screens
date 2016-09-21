/**
* Copyright (c) 2000-present Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/
import UIKit
import LiferayScreens


public class CommentAddView_westeros: CommentAddView_default, UITextViewDelegate {

	@IBOutlet weak var addCommentTextView: UITextView? {
		didSet {
			self.addCommentTextView?.layer.borderColor = UIColor(red: 0.9, green: 0.9, blue: 0.9, alpha: 1.0).CGColor
			self.addCommentTextView?.layer.borderWidth = 1.0
			self.addCommentTextView?.layer.cornerRadius = 5
			self.addCommentTextView?.delegate = self
		}
	}
	@IBOutlet weak var sendCommentButton: UIButton?
	@IBOutlet weak var placeholderLabel: UILabel?

	public override var body: String {
		get {
			return addCommentTextView?.text ?? ""
		}
		set {
			addCommentTextView?.text = newValue
			updateButton()
			updateLabel()
		}
	}


	//MARK: Public methods

	override public func updateButton() {
		sendCommentButton?.enabled = !(addCommentTextView?.text?.isEmpty ?? true)
	}

	public func updateLabel() {
		placeholderLabel?.hidden = !(addCommentTextView?.text?.isEmpty ?? true)
	}


	//MARK: BaseScreenletView

	public override func onShow() {
		super.onShow()
		let previousText = self.sendCommentButton?.titleLabel?.text
		self.sendCommentButton?.titleLabel?.text = previousText?.uppercaseString
	}


	//MARK: UITextViewDelegate

	public func textViewDidChange(textView: UITextView) {
		updateButton()
		updateLabel()
	}
}
