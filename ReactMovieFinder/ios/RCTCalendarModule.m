//
//  RCTCalendarModule.m
//  ReactMovieFinder
//
//  Created by juan sebastian gomez on 8/02/22.
//

#import "RCTCalendarModule.h"
#import <Foundation/Foundation.h>
#import <React/RCTLog.h>

@implementation RCTCalendarModule

// To export a module named RCTCalendarModule
RCT_EXPORT_MODULE(CalendarModuleFoo);

RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location)
{
  RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
  NSLog(@"Pretending to create an event %@ at %@", name, location);
  dispatch_async(dispatch_get_main_queue(), ^(void) {
  UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"ROFL"
                                                      message:@"Native view integration is here."
                                                      delegate:self
                                                      cancelButtonTitle:@"OK"
                                                      otherButtonTitles:nil];
  [alert show];
  });
}

@end
