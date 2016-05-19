$(function(){
	var copys = $('.copys');
	ZeroClipboard.setDefaults( { moviePath: 'http://seller.dhgate.com/prodmanage/js/shelf/ZeroClipboard.swf' } );
	copys.each(function(){
		  var clip =  new ZeroClipboard( this);
		  $(window).resize(function(){
		  		clip.reposition();
		  });
		  //
		  clip.on( 'complete', function ( client, args ) {
			  //alert("Copied text to clipboard: " + args.text );
			   alert(" 按Ctrl + V ,粘贴给你的好友吧 ！");
			} );
	});
});