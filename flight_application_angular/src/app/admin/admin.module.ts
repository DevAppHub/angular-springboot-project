import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminFooterComponent } from './admin-footer/admin-footer.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminHeaderComponent } from './admin-header/admin-header.component';
import { AdminSchedulesComponent } from './admin-schedules/admin-schedules.component';
import { DiscountComponent } from './discount/discount.component';
import { ManageAirlinesComponent } from './manage-airlines/manage-airlines.component';
import { ReportsComponent } from './reports/reports.component';
import { CommonModule } from '@angular/common';
import { ScheduleService } from './service/schedule.service';
import { DiscountService } from './service/discount.service';
import { AirlinesService } from './service/airlines.service';
import { LoginService } from '../admin/service/login.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpInterceptorService } from './service/HttpInterceptorService';
import { AdminToastComponent } from './admin-toast/admin-toast.component';
import { AdminToastService } from './service/admin-toast.service';
import { ReportService } from './service/reportService';



@NgModule({
    declarations: [
        AdminComponent,
        AdminLoginComponent,
        AdminFooterComponent,
        AdminHeaderComponent,
        AdminSchedulesComponent,
        DiscountComponent,
        ManageAirlinesComponent,
        ReportsComponent,
        AdminToastComponent
    ],
    imports: [
        CommonModule,
        AdminRoutingModule,
        ReactiveFormsModule,
        NgbModule
    ],
    providers: [
        AdminToastService,ReportService,
        ScheduleService, DiscountService, AirlinesService, LoginService
       
    ],
    bootstrap: [AdminComponent]
})
export class AdminModule { }
